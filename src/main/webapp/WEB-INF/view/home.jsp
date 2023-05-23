<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Blog Home - Start Bootstrap Template</title>
    <!-- Favicon-->
<%--    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />--%>
    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>" />

    <!-- Core theme CSS (includes Bootstrap)-->
<%--    <link href="resources/css/styles.css" rel="stylesheet" />--%>
    <link href="<c:url value="/resources/css/styles.css"/>"  rel="stylesheet" />

</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#!">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#!">Contact</a></li>
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Blog</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page header with logo and tagline-->
<header class="py-5 bg-light border-bottom mb-4">
    <div class="container">
        <div class="text-center my-5">
<%--        <h1 class="fw-bolder">Welcome to Blog Home!</h1>--%>
            <c:set var="lang" value="${lang}"/>
            <fmt:setLocale value="${lang}"/>
<%--        <fmt:setLocale value="en"/>--%>
<%--        <fmt:setLocale value="uk"/>--%>
            <h1 class="fw-bolder">
                <fmt:setBundle basename="messages" var="homePage"/>
                <fmt:message key="nameOfHomePage" bundle="${homePage}"></fmt:message>
            </h1>
            <p class="lead mb-0">A Bootstrap 5 starter layout for your next blog homepage</p>
        </div>
    </div>
</header>
<!-- Page content-->
<div class="container">
    <div class="summary row">
        <!-- Blog entries-->
        <div class="col-lg-8">
            <!-- Featured blog post-->
            <c:forEach items="${posts}" var="post" varStatus="s">
                <c:choose>
                    <c:when test="${s.count == 1}">
                        <div class="card mb-4">
                            <a href="<c:url value="/post"/>" ><img class="card-img-top" data-id="${post.id}" src="<c:url value="${post.imagePath}"/>" alt="..."/></a>
                            <div class="card-body">
                                <div class="small text-muted">${post.published}</div>
                                <div class="small text-muted">Author: ${post.user.firstName} ${post.user.lastName}</div>
                                <h2 class="card-title h4">${post.title}</h2>
                                <div class="card-text">${post.content}</div>
                                <form action="<c:url value="/post"/>">
<%--                            <form action="<c:url value="/post"><c:param name="id" value="${post.id}"/></c:url>">--%>
                                    <button class="btn btn-primary" data-id="${post.id}" type="submit">Read more →</button>
                                </form>
                                    <%--  <a class="details btn btn-primary" href="<c:url value="/post"/>" id="${post.id}">Read more →</a>--%>
                            </div>
                        </div>
                    </c:when>
                </c:choose>
            </c:forEach>
            <!-- Nested row for non-featured blog posts-->
            <div class="row">
                <c:set var="posts" value="${sessionScope.posts}"/>
                <div class="col-lg-6">
                    <!-- Blog post-->
                    <c:forEach items="${posts}" var="post" varStatus="s">
                        <c:choose>
                            <c:when test="${s.index % 2 == 0}">
                                <div class="card mb-4">
                                    <a href="<c:url value="/post"/>" ><img class="card-img-top" data-id="${post.id}" src="<c:url value="${post.imagePath}"/>" alt="..."/></a>
                                    <div class="card-body">
                                        <div class="small text-muted">${post.published}}</div>
                                        <div class="small text-muted">Author: ${post.user.firstName} ${post.user.lastName}</div>
                                        <h2 class="card-title h4">${post.title}</h2>
                                        <div class="card-text">${post.content}</div>
                                        <form action="<c:url value="/post"></c:url>">
                                            <button class="btn btn-primary"  data-id="${post.id}"  type="submit">Read more →</button>
                                        </form>
<%--                                        <a class="details btn btn-primary" href="<c:url value="/post"/>" id="${post.id}">Read more →</a>--%>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
                <div class="col-lg-6">
                    <!-- Blog post-->
                    <c:forEach items="${posts}" var="post" varStatus="s">
                        <c:choose>
                            <c:when test="${s.index % 2 == 1}">
                                <div class="card mb-4">
                                    <a href="<c:url value="/post"/>" ><img class="card-img-top" data-id="${post.id}" src="<c:url value="${post.imagePath}"/>" alt="..." /></a>
                                    <div class="card-body">
                                        <div class="small text-muted">${post.published}}</div>
                                        <div class="small text-muted">Author: ${post.user.firstName} ${post.user.lastName}</div>
                                        <h2 class="card-title h4">${post.title}</h2>
                                        <div class="card-text">${post.content}</div>
                                        <form action="<c:url value="/post"></c:url>">
                                            <button class="details btn btn-primary" data-id="${post.id}"  type="submit">Read more →</button>
                                        </form>
<%--                                        <a class="btn btn-primary" href="<c:url value="/post"/>" id="${post.id}">Read more →</a>--%>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </c:forEach>

                </div>
            </div>
            <!-- Pagination-->
            <nav aria-label="Pagination">
                <hr class="my-0" />
                <ul class="pagination justify-content-center my-4">
                    <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1" aria-disabled="true">Newer</a></li>
                    <li class="page-item active" aria-current="page"><a class="page-link" href="#!">1</a></li>
                    <li class="page-item"><a class="page-link" href="#!">2</a></li>
                    <li class="page-item"><a class="page-link" href="#!">3</a></li>
                    <li class="page-item disabled"><a class="page-link" href="#!">...</a></li>
                    <li class="page-item"><a class="page-link" href="#!">15</a></li>
                    <li class="page-item"><a class="page-link" href="#!">Older</a></li>
                </ul>
            </nav>
        </div>
        <!-- Side widgets-->
        <div class="col-lg-4">
            <!-- Search widget-->
            <div class="card mb-4">
                <div class="card-header">Search</div>
                <div class="card-body">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Enter search term..." aria-label="Enter search term..." aria-describedby="button-search" />
                        <button class="btn btn-primary" id="button-search" type="button">Go!</button>
                    </div>
                </div>
            </div>
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">Categories</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">Web Design</a></li>
                                <li><a href="#!">HTML</a></li>
                                <li><a href="#!">Freebies</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">JavaScript</a></li>
                                <li><a href="#!">CSS</a></li>
                                <li><a href="#!">Tutorials</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">Side Widget</div>
                <div class="card-body">You can put anything you want inside of these side widgets. They are easy to use, and feature the Bootstrap 5 card component!</div>
            </div>
        </div>
    </div>
</div>
<!-- Footer-->
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script src="resources/js/scripts.js"></script>--%>
<script src="<c:url value="/resources/js/scripts.js"/>" ></script>
<script>
    function getAttributeFromTarget(target) {
        const postId = target.getAttribute('data-id');
        fetch("<c:url value="/post"/>", {
            method: "POST",
            headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
            body: `id=\${postId}`
        }).then(response => {
            console.log(response.status);
            // if (response.redirected) {
            //     location = '/';
            // }
        }).catch(error => {
            alert(error);
        });
    }

    const divSummary = document.querySelector(".summary");
    divSummary.addEventListener("click", e => {
        if (e.target.nodeName === 'BUTTON') {
            const target1 = e.target;
            getAttributeFromTarget(target1);
        }
        if (e.target.nodeName === 'IMG' ) {
            const target2 = e.target;
            getAttributeFromTarget(target2);
        }
    });
</script>

</body>
</html>