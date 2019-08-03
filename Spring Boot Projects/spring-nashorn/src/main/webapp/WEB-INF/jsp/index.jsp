<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello React</title>
    <script type="text/javascript" src="js/react.js"></script>
    <script type="text/javascript" src="js/react-dom.js"></script>
    <script type="text/javascript" src="js/react-dom-server.js"></script>
</head>
<body>
<div id="root">${content}</div>
  
<script type="text/javascript" src="index.js"></script>
<script type="text/javascript">
        renderClient(["Kumar", "Chandrakant"]);
</script>

</body>
</html>