package com.kyeonjuk.user.domain;

// name 값 유효성 검사를 위한 domain
public class UserInfo {

    private String name;
    private String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void patchProfileImageUrl(String imageUrl) {
        profileImageUrl = imageUrl;
    }

    public void patchProfileName(String newName) {
        name = newName;
    }
}
