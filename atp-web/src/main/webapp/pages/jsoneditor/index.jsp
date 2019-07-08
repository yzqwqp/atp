<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String domain = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<link rel="stylesheet" href="<%=path%>/css/jsoneditor/jsoneditor.css"/>
	
    <style>
        body {
          padding: 0 70px;
        }
        #json {
          margin: 10px 10px 10px 32px;
          width: 50%;
          min-height: 70px;
        }
        h1 {
          font-family: Arial;
          color: #EBBC6E;
          text-align: center;
          text-shadow: 1px 1px 1px black;
          border-bottom: 1px solid gray;
          padding-bottom: 50px;
          width: 500px;
          margin: 20px auto;
        }
        h1 img {
          float: left;
        }
        h1 b {
          color: black;
          font-weight: normal;
          display: block;
          font-size: 12px;
          text-shadow: none;
        }

        #legend {
          display: inline;
          margin-left: 30px;
        }
        #legend h2 {
           display: inline;
           font-size: 18px;
           margin-right: 20px;
        }
        #legend a {
          color: white;
          margin-right: 20px;
        }
        #legend span {
          padding: 2px 4px;
          -webkit-border-radius: 5px;
          -moz-border-radius: 5px;
          border-radius: 5px;
          color: white;
          font-weight: bold;
          text-shadow: 1px 1px 1px black;
          background-color: black;
        }
        #legend .string  { background-color: #009408; }
        #legend .array   { background-color: #2D5B89; }
        #legend .object  { background-color: #E17000; }
        #legend .number  { background-color: #497B8D; }
        #legend .boolean { background-color: #B1C639; }
        #legend .null    { background-color: #B1C639; }

        #expander {
          cursor: pointer;
          margin-right: 20px;
        }

        #footer {
          font-size: 13px;
        }

        #rest {
          margin: 20px 0 20px 30px;
        }
        #rest label {
          font-weight: bold;
        }
        #rest-callback {
          width: 70px;
        }
        #rest-url {
          width: 700px;
        }
        label[for="json"] {
          margin-left: 30px;
          display: block;
        }
        #json-note {
          margin-left: 30px;
          font-size: 12px;
        }

        .addthis_toolbox {
          position: relative;
          top: -10px;
          margin-left: 30px;
        }

        #disqus_thread {
          margin-top: 50px;
          padding-top: 20px;
          padding-bottom: 20px;
          border-top: 1px solid gray;
          border-bottom: 1px solid gray;
        }

    </style>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.js"></script>
	<script type="text/javascript">
	
		var path='<%=path%>';
		var _gaq = _gaq || [];
	    _gaq.push(['_setAccount', 'UA-5029684-7']);
	    _gaq.push(['_trackPageview']);
	    
	</script>
</head>

<body class="page-body">

	<div id="legend"> 
        <span id="expander">Expand all</span>
        <span class="array">array</span>
        <span class="object">object</span>
        <span class="string">string</span>
        <span class="number">number</span>
        <span class="boolean">boolean</span>
        <span class="null">null</span>
        <span>Remove item by deleting a property name.</span>
    </div>
 
    <pre id="path"></pre>
    <div id="editor" class="json-editor"></div> 
    <textarea id="json" rows="50"></textarea><br/>
 
    <script src="<%=path%>/js/jsoneditor/json2.js"></script>
    <script src="<%=path%>/js/jsoneditor/jquery.min.js"></script>
    <script src="<%=path%>/js/jsoneditor/jquery.jsoneditor.js"></script>
    <script src="<%=path%>/js/jsoneditor/jsoneditor.js"></script>
	
	
</body>
</html>