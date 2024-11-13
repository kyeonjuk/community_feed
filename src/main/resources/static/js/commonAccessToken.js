// 페이지 로딩 시점에 Authorization 헤더 설정
document.addEventListener("DOMContentLoaded", function() {
  const token = localStorage.getItem('accessToken');

  if (token) {
    // axios 기본 헤더에 Authorization 헤더 설정 (페이지 로딩 전에 설정)
    axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  }
});

// 인증된 데이터를 요청 (예: 사용자 정보)
async function fetchUserData(pagePath) {
  try {
    // 서버에 인증된 데이터를 요청 (헤더에 자동으로 Authorization 포함)
    const response = await axios.get(pagePath);  // 예: '/user/data'는 인증된 사용자 정보 API

    // 서버에서 반환된 데이터 처리
    console.log('사용자 데이터:', response.data);

  } catch (error) {
    console.error('데이터 요청 오류:', error);
    if (error.response) {
      console.error('서버 응답 오류:', error.response.data);
    }
  }
}
