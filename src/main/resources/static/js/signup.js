let emailStep=1;
let storedEmail='';
let isNicknameValid = false;
let isEmailCertified = false;
let isPasswordMatched = false;



function validateNickname() {
    const nicknameValue = document.getElementById('floatingName').value;

    fetch('/nickname', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nickname: nicknameValue
        })
    })
        .then(response => response.json())
        .then(data => {
            console.log('응답: ', data);
            const msg1 = document.getElementById('nicknameMsg');
            msg1.textContent = data.data;
            msg1.classList.remove('text-danger', 'text-primary');

            if (data.code === 400) {
                msg1.classList.add('text-danger');
                isNicknameValid = false
            } else if (data.code === 200) {
                msg1.classList.add('text-primary');
                enableInput();
                document.getElementById('floatingPassword').disabled = false;
                document.getElementById('floatingPWCheck').disabled = false;
                isNicknameValid = true
                updateSignUpButton();
            }

        })
}
function  enableInput(){
    document.getElementById('floatingEmail').disabled = false;
    document.getElementById('floatingPassword').disabled = false;
    document.getElementById('floatingPWCheck').disabled = false;

    const emailBtn = document.querySelector('#floatingEmail')
        .closest('.row')
        .querySelector('button');
    if(emailBtn) emailBtn.disabled = false;
}


function showCodeInput() {
    document.getElementById('floatingEmail').style.display = 'none';
    document.getElementById('codeInput').style.display = 'block';
}

function showEmailInput() {
    document.getElementById('codeInput').style.display = 'none';
    document.getElementById('floatingEmail').style.display = 'block';
}

function  emailProcess() {
    const emailInput = document.getElementById('floatingEmail');
    const emailMsg = document.getElementById('emailMsg');
    const codeInput = document.getElementById('codeInput'); /* @@@@@수정@@@@@ */
    const emailBtn = document.getElementById("emailBtn");

    if(emailStep === 1){
        const emailValue = emailInput.value.trim();
        const emailRegex = /^\S+@\S+\.\S+$/;
        if (!emailRegex.test(emailValue)) {
            emailMsg.textContent = '올바른 이메일 형식을 입력하세요.';
            emailMsg.classList.add('text-danger');
            return;
        }
        fetch('/mail/code', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: emailValue })
        })
            .then(res => res.json())
            .then(data => {
                console.log('이메일 응답:', data);
                emailMsg.classList.remove('text-danger', 'text-primary');

                if (data.code === 200) {
                    emailMsg.textContent = data.data;
                    emailMsg.classList.add('text-primary');

                    emailInput.placeholder = '인증번호 입력';
                    document.getElementById('emailLabel').textContent = '인증번호';

                    emailStep = 2;
                    storedEmail = emailValue;
                    showCodeInput();
                } else {
                    emailMsg.textContent = data.data;
                    emailMsg.classList.add('text-danger');
                }
            })

    }else if (emailStep === 2) {
        const codeValue = codeInput.value.trim();
        if (!codeValue) {
            emailMsg.textContent = '인증번호를 입력하세요.';
            emailMsg.classList.add('text-danger');
            return;
        }
        fetch('/user/email/code', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email: storedEmail, code: codeValue })
        })
            .then(res => res.json())
            .then(data => {
                console.log('인증번호 확인 응답:', data);
                emailMsg.classList.remove('text-danger', 'text-primary');

                if (data.code === 200) {
                    emailMsg.textContent = '인증이 완료되었습니다.';
                    emailMsg.classList.add('text-primary');

                    emailInput.placeholder = '이메일';
                    document.getElementById('emailLabel').textContent = '이메일';

                    emailStep = 1;
                    showEmailInput();
                    emailInput.value = storedEmail;
                    isEmailCertified = true;
                    updateSignUpButton();
                }else {
                    emailMsg.textContent = '인증번호가 일치하지 않습니다. 다시 입력하세요.';
                    emailMsg.classList.add('text-danger');
                    isEmailCertified = false;
                    updateSignUpButton();
                }
            })
    }
}


function updateSignUpButton() {
    const signUpBtn = document.getElementById('signUpBtn');

    if (isNicknameValid && isEmailCertified && isPasswordMatched) {
        signUpBtn.disabled = false;
    } else {
        signUpBtn.disabled = true;
    }
}

function checkPasswordMatch() {
    const pw1 = document.getElementById('floatingPassword').value;
    const pw2 = document.getElementById('floatingPWCheck').value;
    const pwMsgEl = document.getElementById('pwCheckMsg');

    if (!pw1 || !pw2) {
        pwMsgEl.textContent = '';
        pwMsgEl.classList.remove('text-danger', 'text-success');
        isPasswordMatched = false;
        updateSignUpButton();
        return;
    }

    if (pw1 === pw2) {
        pwMsgEl.textContent = '비밀번호가 일치합니다.';
        pwMsgEl.classList.remove('text-danger');
        pwMsgEl.classList.add('text-success');
        isPasswordMatched = true;
    } else {
        pwMsgEl.textContent = '비밀번호가 일치하지 않습니다.';
        pwMsgEl.classList.remove('text-success');
        pwMsgEl.classList.add('text-danger');
        isPasswordMatched = false;
    }
    updateSignUpButton();

}


window.addEventListener('DOMContentLoaded', () => {
    const pwField = document.getElementById('floatingPassword');
    const pwCheckField = document.getElementById('floatingPWCheck');


    pwField.addEventListener('input', checkPasswordMatch);
    pwCheckField.addEventListener('input', checkPasswordMatch);
});

window.addEventListener('DOMContentLoaded', () => {
    const email = document.getElementById('floatingEmail');
    const pw = document.getElementById('floatingPassword');
    const pwCheck = document.getElementById('floatingPWCheck');

    function blockInputIfNicknameInvalid(e) {
        if (!isNicknameValid) {

            const nickField = document.getElementById('floatingName');
            nickField.classList.add('border', 'border-danger');


            setTimeout(() => {
                nickField.classList.remove('border', 'border-danger');
            }, 3000);


            e.target.blur();
            e.preventDefault();
        }
    }
    [email, pw, pwCheck].forEach(inputEl => {
        inputEl.addEventListener('focus', blockInputIfNicknameInvalid);
    });
});

