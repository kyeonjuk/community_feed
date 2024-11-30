package com.kyeonjuk.user.ui.dto;

import org.springframework.web.multipart.MultipartFile;

public record PatchProfileImageRequestDto(MultipartFile profileImageUrl) {

}
