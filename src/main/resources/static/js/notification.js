// 페이지 로드 시 알림 가져오기
window.onload = fetchUnreadNotifications;

async function fetchUnreadNotifications() {
  try {
    const response = await fetch('/user/notification/getUnread', {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken') // AccessToken 필요
      }
    });

    if (response.ok) {
      const data = await response.json();
      const notifications = data.value;
      console.log(notifications);

      // 알림 표시
      displayNotifications(notifications);
    } else {
      console.error('Failed to fetch unread notifications');
    }
  } catch (error) {
    console.error('Error:', error);
  }
}

// 알림 표시 함수
function displayNotifications(notifications) {
  const container = document.getElementById('notificationContainer');
  const noNotificationsMessage = document.getElementById('noNotificationsMessage');

  // 알림 초기화
  container.innerHTML = '';

  if (notifications.length === 0) {
    // 읽지 않은 알림이 없을 때
    noNotificationsMessage.style.display = 'block';
  } else {
    // 알림이 있을 때
    noNotificationsMessage.style.display = 'none';

    notifications.forEach(notification => {
      const item = document.createElement('div');
      item.className = 'notification-item';
      item.innerHTML = `
        <div class="notification-header">
          <i class="fas fa-bell"></i>
          <span class="notification-message">${notification.body}</span>
        </div>
        <div class="notification-footer">
          <a href="#">자세히 보기</a>
        </div>
      `;

      // 클릭 시 알림 읽음 처리 및 URL로 이동
      item.addEventListener('click', async (e) => {
        e.preventDefault(); // 기본 링크 동작 막기

        // 알림 읽음 처리
        await markNotificationAsRead(notification.id);

        // URL로 이동
        window.location.href = notification.contentUrl;
      });

      container.appendChild(item);
    });
  }
}

// 알림 읽음 처리 함수
async function markNotificationAsRead(notificationId) {
  try {
    const response = await fetch(`/user/notification/markAsRead/${notificationId}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('accessToken') // AccessToken 필요
      }
    });

    if (!response.ok) {
      console.error(`확인할 수 없는 알림 입니다.`);
    }
  } catch (error) {
    console.error('Error:', error);
  }
}
