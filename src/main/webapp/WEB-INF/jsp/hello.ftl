<html>
<head>
    <title>FreeMarker</title>
    <link href="./css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div class="navbar">
    <div class="navbar-inner">
        <a class="brand" href="#">Title</a>
        <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
        </ul>
    </div>
</div>

<form class="form-horizontal" action="./signin">
    <div class="control-group">
        <label class="control-label" for="inputEmail">Email</label>
        <div class="controls">
            <input type="text" id="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="control-group">
        <label class="control-label" for="inputPassword">Password</label>
        <div class="controls">
            <input type="password" id="inputPassword" placeholder="Password">
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <label class="checkbox">
                <input type="checkbox"> Remember me
            </label>
            <button type="submit" class="btn">Sign in</button>
        </div>
    </div>
</form>
</body>
</html>