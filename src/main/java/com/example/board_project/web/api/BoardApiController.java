package com.example.board_project.web.api;

import com.example.board_project.config.auth.PrincipalDetails;
import com.example.board_project.domain.board.Board;
import com.example.board_project.domain.board.BoardRepository;
import com.example.board_project.service.BoardService;
import com.example.board_project.service.ImageService;
import com.example.board_project.web.dto.CMRespDTO;
import com.example.board_project.web.dto.board.BoardDto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    @Value("${file.upload}")
    private String uploadFolder;

    private final ImageService imageService;
    private final BoardService boardService;

    @PostMapping("/api/user/board/post")
    public ResponseEntity<?> post(@AuthenticationPrincipal PrincipalDetails principalDetails,
        @RequestBody BoardDto boardDto) {

        int userId = principalDetails.getUser().getId();
        Board boardEntity = boardService.createBoard(boardDto, userId);
        imageService.saveImages(boardDto.getFiles(), boardEntity.getId());

        return new ResponseEntity<>(new CMRespDTO<>(1, "게시물 작성에 성공하였습니다", null),
            HttpStatus.CREATED);
    }

    @PostMapping("/api/user/upload/image")
    public String uploadImage(HttpServletRequest request,
        HttpServletResponse response, MultipartFile upload) {
        OutputStream os = null;

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");

        String callback = null;
        String fileUrl = null;

        try {
            UUID uuid = UUID.randomUUID();
            String extension = FilenameUtils.getExtension(upload.getOriginalFilename());

            byte[] bytes = upload.getBytes();
            String imgUploadPath = uploadFolder + "\\image\\" + uuid + "." + extension;
            System.out.println(imgUploadPath);

            os = new FileOutputStream(imgUploadPath);
            os.write(bytes);
            os.flush();

//            callback = request.getParameter("CKEditorFuncNum");
            fileUrl = "./upload/image/" + uuid + "." + extension;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(os != null) {
                    os.close();
                    os = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//            "<script type='text/javascript'>"
//            + "window.parent.CKEDITOR.tools.callFunction("
//            + callback+",'"+ fileUrl+"','이미지를 업로드하였습니다.')"
//            +"</script>"
        return fileUrl;
    }


}
