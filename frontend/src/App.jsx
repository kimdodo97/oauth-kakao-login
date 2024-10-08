import { BrowserRouter, Routes, Route } from 'react-router-dom';
import KakaoLogin from './components/KakaoLogin';
import { KAKAO_AUTH_URL } from './OAuth';
import Main from './components/Main';

const App = () => {
    return (
        <div className='App'>
            <BrowserRouter>
                <Routes>
                <Route path='/' element={<Main/>}/>
                <Route
                  path="/login/oauth2/callback/kakao" //redirect_url
                  element={<KakaoLogin />} //당신이 redirect_url에 맞춰 꾸밀 컴포넌트
                />
                </Routes>
            </BrowserRouter>
        </div>
    );
};

export default App;