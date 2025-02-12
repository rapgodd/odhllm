    function emailCheck() {
    const emailValue = document.getElementById('email').value;
    const resultElem = document.getElementById('email-result');
    resultElem.innerText = '';

    fetch('/email', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify({ email: emailValue })
})
    .then(response => response.text()) // 서버에서 문자열을 그대로 받음
    .then(serverMessage => {

    // 1) 화면에 표시
    resultElem.innerText = serverMessage;

    // 2) 색상 처리
    if (serverMessage.includes("이미 사용중")
    || serverMessage.includes("형식이 올바르지")) {
    resultElem.style.color = 'red';
} else {
    resultElem.style.color = 'green';
}
})
    .catch(error => {
    console.error('Error:', error);
});
}
