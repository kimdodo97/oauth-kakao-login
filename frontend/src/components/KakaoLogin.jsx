import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import axios from "axios";

const KakaoLogin = (props) => {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get("code");

  useEffect(() => {
    const kakaoLogin = async () => {
      await axios({
        method: "POST",
        url: `http://localhost:8080/login/oauth2/kakao?code=${code}`,
        headers: {
          "Content-Type": "application/json;charset=utf-8", 
          // "Access-Control-Allow-Orig in": "*", 
        },
      }).then((res) => { 
        console.log(res);
        //계속 쓸 정보들( ex: 이름) 등은 localStorage에 저장해두자
        if(res.status===200){
            navigate("/");
        }       
      });
    };
    kakaoLogin();
  }, [code]);

  return (
    <div className="LoginHandeler">
      <div className="notice">
        <p>로그인 중입니다.</p>
        <p>잠시만 기다려주세요.</p>
        <div className="spinner"></div>
      </div>
    </div>
  );
};

export default KakaoLogin;