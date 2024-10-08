import React from 'react';
import PropTypes from 'prop-types';
import { KAKAO_AUTH_URL } from '../OAuth';
import kakao from '../assets/kakao.png'
const Main = () => {
    return (
        <div>
             <a href={KAKAO_AUTH_URL} className="kakaobtn">
              <img src={kakao} />
            </a>

        </div>
    );
};

Main.propTypes = {};

export default Main;