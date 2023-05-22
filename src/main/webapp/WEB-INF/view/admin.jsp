<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Blog Home - Start Bootstrap Template</title>
    <link rel="icon" type="image/x-icon" href="<c:url value="/resources/assets/favicon.ico"/>"/>
    <link href="<c:url value="/resources/css/styles.css"/>" rel="stylesheet"/>
    <style>
        #title.input,
        #imagepath.input {
            margin-top: 30px;
        }

        .input {
            margin-top: 30px;
        }

        .ml-5 {
             margin-left: 20px;
        }

        #but-post.btn {
            display: block;
            margin-top: 30px;
            width: 100%;
            margin-bottom: 30vh;
        }
        input#file-upload-button{

            background-color: #0d6efd;
            border-radius: 3px;
        }

    </style>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="#!">Start Bootstrap</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
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
<main>
    <c:set var="lang" value="${lang}"/>
    <fmt:setLocale value="${lang}"/>
    <div class="container">
        <div class="row">
            <div class="col-xl-3">
                <form action="<c:url value="/home"/>">
                    <button class="btn btn-primary mt-5" type="submit" id="but-home">Go home page</button>
                </form>
            </div>
            <div class="col-xl-6 text-center my-5">
                <h1 class="fw-bolder">
                    <fmt:setBundle basename="messages" var="admin"/>
                    <fmt:message key="admin" bundle="${admin}"></fmt:message>
                </h1>
                <form class="form" method="post" enctype="multipart/form-data">
                    <%-- title --%>
                    <fmt:setBundle basename="messages" var="title"/>
                    <input type="text" class="input form-control" id="title" name="title"
                           placeholder="<fmt:message  key="title" bundle="${title}"></fmt:message>">
                    <%--author--%>
<%--                    <select class="input form-select" aria-label="Default select example" id="author" name="author">--%>
<%--                        <c:forEach items="${sessionScope.users}" var="user" varStatus="s">--%>
<%--                            <option value="${user.id} ">${user.firstName}  ${user.lastName}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
                    <%--image-path--%>
                    <fmt:setBundle basename="messages" var="imagepath"/>
                    <div class="input mb-2">
                         <label class="btn btn-primary text-start" for="file"><fmt:message  key="imagepath" bundle="${imagepath}"></fmt:message>(.jpg, .jpeg, .png)</label>
                         <input class="btn-success" type="file" id="file" name="file"
                                accept=".jpg, .jpeg, .png" value="Choose file"
                                style="opacity: 0;" required/>
                   </div>
<%--                    <input type="text" class="input form-control" id="imagepath" name="imagepath"--%>
<%--                           placeholder="<fmt:message  key="imagepath" bundle="${imagepath}"></fmt:message>">--%>
                    <%--content--%>
                    <fmt:setBundle basename="messages" var="content"/>
                    <textarea class="input" id="content" name="content">
                         <fmt:message  key="content" bundle="${content}"></fmt:message>
                    </textarea>
                    <%--draft--%>
                    <div class="input input-group-text">
                        <label for="draft">Draft</label>
                        <input class="form-check-input mt-0 ml-5" id="draft" type="checkbox" name="draft">
                    </div>
                    <button type="submit" class="btn btn-primary" id="but-post">Ok</button>
                </form>
            </div>
            <div class="col-xl-3"></div>
        </div>
    </div>



</main>
<footer class="py-5 bg-dark">
    <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2023</p></div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.tiny.cloud/1/smgt7motjmt39brlvr481ll1m7kn9fycui3egjbpp7vdwodp/tinymce/6/tinymce.min.js"
        referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: '#content'
    });
</script>

<%--<script src="<c:url value="/resources/js/scripts.js"/>" ></script>--%>
</body>
</html>