const CLIENT_ID = '210aaf5b574f67e81af3a7b82d8b8179';
const REDIRECT_URI = 'http://localhost:5173/login/oauth2/callback/kakao';
export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;