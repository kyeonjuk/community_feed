$(document).ready(function () {
    // 검색 버튼 클릭 시
    $('#searchBtn').on('click', performSearch);

    // Enter 키 입력 시 검색
    $('#searchInput').on('keypress', function (e) {
        if (e.which === 13) {
            performSearch();
        }
    });

    // 검색 함수
    function performSearch() {
        const query = $('#searchInput').val().trim();
        if (!query) {
            alert('검색어를 입력하세요.');
            return;
        }

        // API 호출
        $.ajax({
            url: `/user/search/${query}`, // API 엔드포인트
            method: 'GET',
            success: function (response) {
                if (response && response.value) {
                    displaySearchResults(response.value);
                } else {
                    displayNoResults();
                }
            },
            error: function () {
                alert('검색 중 오류가 발생했습니다.');
            }
        });
    }

    // 검색 결과 표시 함수
    function displaySearchResults(results) {
        const list = $('#searchResultsList');
        list.empty(); // 기존 결과 제거

        results.forEach(function (user) {
            const listItem = `
                <a href="/model/user/profile/${user.id}">
                  <li style="padding: 10px; border-bottom: 1px solid #f0f0f0; display: flex; align-items: center;">
                      <img src="/uploads/images/${user.profileImageUrl || 'Default-Profile.png'}" style="width: 40px; height: 40px; border-radius: 50%; margin-right: 10px;">
                      <span>${user.name}</span>
                  </li>
                </a>`;
            list.append(listItem);
        });

        openPopup('#searchResultModal'); // 검색 결과 모달 열기
    }

    // 검색 결과가 없을 때의 처리
    function displayNoResults() {
        const list = $('#searchResultsList');
        list.empty(); // 기존 결과 제거
        list.append('<li style="text-align: center; padding: 10px;">검색 결과가 없습니다.</li>');
        openPopup('#searchResultModal'); // 검색 결과 모달 열기
    }

    // 모달 열기 함수
    function openPopup(selector) {
        $('.modal-info').fadeOut(200);  // 다른 모든 모달을 먼저 닫기
        $(selector).fadeIn(200);        // 선택한 모달만 열기
    }

    // 모달 닫기 함수
    function closePopup(selector) {
        $(selector).fadeOut(200); // 모달을 서서히 숨기기
    }

    // 모달 닫기 버튼에 대한 이벤트 처리
    $('.close-btn').on('click', function () {
        closePopup('#searchResultModal');
    });

    // 알림 아이콘 상태 초기화
    checkUnreadNotifications(); // 페이지 로드 시 알림 상태 확인

    // 알림 아이콘 처리
    async function checkUnreadNotifications() {
        try {
            const response = await fetch('/user/notification/getUnreadCount', {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('accessToken')}`, // Access Token
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const data = await response.json(); // { "success": true, "data": 5 }
                const unreadCount = data.value;      // 읽지 않은 알림 수 가져오기
                updateNotificationIcon(unreadCount > 0); // 0보다 크면 아이콘을 업데이트
            } else {
                console.error('Failed to fetch notifications.');
            }
        } catch (error) {
            console.error('Error fetching notifications:', error);
        }
    }


    // 알림 아이콘 상태 업데이트 함수
    function updateNotificationIcon(hasUnreadNotifications) {
        const icon = $('#notificationIcon'); // jQuery로 접근

        if (hasUnreadNotifications) {
            icon.removeClass('far').addClass('fas'); // Regular -> Solid
            icon.css('color', '#f39c12');                // 빨간색으로 변경
        } else {
            icon.removeClass('fas').addClass('far'); // Solid -> Regular
            icon.css('color', 'gray');               // 회색으로 변경
        }
    }
});
