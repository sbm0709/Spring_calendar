const form = document.forms.item(0);
const modifyBtn = document.getElementById('modify-btn');
const modifyCancelBtn = document.getElementById('cancel-btn');
modifyBtn.onclick = () => {
    // active 되어있는 상태 => 수정 상태
    if(modifyBtn.hasAttribute('active')){
        // 한번 더 누를시 실제로 수정
        form.action = '/board/modify';
        form.method = 'post';
        form.submit();
    }
    
    // view 상태이므로, active상태로 변경
    modifyBtn.toggleAttribute('active', true);
    modifyBtn.textContent = '수정적용';
    const formDatas = document.getElementsByClassName('form-data');
    [...formDatas].forEach(formData => {
        formData.toggleAttribute('readonly');
        formData.style.backgroundColor = 'rgba(0,0,0,0.1)';
    });
}

modifyCancelBtn.onclick = () => {
    const formDatas = document.getElementsByClassName('form-data');
    [...formDatas].forEach(formData => {
        formData.toggleAttribute('readonly');
        formData.style.backgroundColor = 'white';
    });
    
    modifyBtn.removeAttribute('active');
    modifyBtn.textContent = '수정하기';
}