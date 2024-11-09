/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {
    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
});

function createPagination(totalItems, currentPage, target, query) {
    const itemsPerPage = 10;
    if (!currentPage) {
        currentPage = 1;
    }

    function renderPagination() {
        const pagination = document.querySelector('.pagination');
        pagination.innerHTML = '';  // 기존 페이지 항목 초기화

        // page index 가 1 이하면 이전 페이지가 없으므로 "Previous" 버튼을 추가하지 않음
        if (currentPage > 1) {
            const prevLi = document.createElement('li');
            prevLi.classList.add('page-item');
            const prevA = document.createElement('a');
            prevA.classList.add('page-link');
            prevA.innerText = 'Previous';
            prevA.href = `/admin/${target}?pageIndex=${currentPage - 1}&pageSize=${itemsPerPage}${query}`;
            prevLi.appendChild(prevA);
            pagination.appendChild(prevLi);
        }

        // page index 가 마지막이면 다음 페이지가 없으므로 "Next" 버튼을 추가하지 않음
        const totalPages = Math.ceil(totalItems / itemsPerPage);
        if (currentPage < totalPages) {
            const nextLi = document.createElement('li');
            nextLi.classList.add('page-item');
            const nextA = document.createElement('a');
            nextA.classList.add('page-link');
            nextA.innerText = 'Next';
            nextA.href = `/admin/${target}?pageIndex=${currentPage + 1}&pageSize=${itemsPerPage}${query}`;
            nextLi.appendChild(nextA);
            pagination.appendChild(nextLi);
        }
    }

    renderPagination();
}