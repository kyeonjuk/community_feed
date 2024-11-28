self.addEventListener('push', function(event) {
  const jsonData = event.data.json();
  const options = {
    body: jsonData.data.message,
    icon: 'logo.jpg',
    badge: 'logo.jpg'
  };
  event.waitUntil(
    self.registration.showNotification('커뮤니티 피드 서비스 알림', options)
  );
});

