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
          <a href="${notification.contentUrl}" target="_blank">자세히 보기</a>
        </div>
      `;

      // 클릭 시 URL로 이동
      item.addEventListener('click', () => {
        window.location.href = notification.contentUrl;
      });

      container.appendChild(item);
    });
  }
}
