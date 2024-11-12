package com.kyeonjuk.admin.ui;

import com.kyeonjuk.admin.ui.dto.GetTableListResponse;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableRequestDto;
import com.kyeonjuk.admin.ui.dto.posts.GetPostTableResponseDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableRequestDto;
import com.kyeonjuk.admin.ui.dto.users.GetUserTableResponseDto;
import com.kyeonjuk.admin.ui.query.AdminTableQueryRepository;
import com.kyeonjuk.admin.ui.query.UserStatsQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatsQueryRepository userStatsQueryRepository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        modelAndView.addObject("result", userStatsQueryRepository.getDailyRegisterUserStats(7));
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {

        if (dto.getName() == null) {
            dto.setName("");
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("users");  // users.html 파일 출력

        GetTableListResponse<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableData(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("userList", result.getTableData());
        modelAndView.addObject("totalCount", result.getTotalCount());

        return modelAndView;
    }

    @GetMapping("/posts")
    public ModelAndView posts(GetPostTableRequestDto dto) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("posts");

        GetTableListResponse<GetPostTableResponseDto> result = adminTableQueryRepository.getPostTableData(dto);
        modelAndView.addObject("requestDto", dto);
        modelAndView.addObject("postList", result.getTableData());
        modelAndView.addObject("totalCount", result.getTotalCount());

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
