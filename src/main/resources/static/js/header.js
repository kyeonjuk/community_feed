$(document).ready(function () {
    // 검색 버튼 클릭 시
    $('#searchBtn').on('click', function () {
        performSearch();
    });

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
                    alert('검색 결과가 없습니다.');
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

        if (results.length === 0) {
            list.append('<li style="text-align: center; padding: 10px;">검색 결과가 없습니다.</li>');
        } else {
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
        }

        openPopup('#searchResultModal'); // 검색 결과 모달 열기
    }

    // 모달 열기 함수
    function openPopup(selector) {
        // 다른 모든 모달을 먼저 닫기
        $('.modal-info').fadeOut(200);
        // 선택한 모달만 열기
        $(selector).fadeIn(200);
    }

    // 모달 닫기 함수
    function closePopup(selector) {
        $(selector).fadeOut(200); // 모달을 서서히 숨기기
    }

    // 모달 닫기 버튼에 대한 이벤트 처리
    $('.close-btn').on('click', function () {
        closePopup('#searchResultModal');
    });
});
