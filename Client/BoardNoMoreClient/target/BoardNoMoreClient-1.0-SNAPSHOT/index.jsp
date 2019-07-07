
<html>
    <head>
        <title>Board No More</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles.css">
        
        <script type = "text/JavaScript" src = "javascripts/imagePreview.js"> </script>

    </head>
    <body>
        <div class="topnav">
            <a href="#index"><b>Home</b></a>
            <a href="about.html">About</a>
        </div>

        <div class="boardGameTable">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Players</th>
                    <th>Playing Time</th>
                    <th>Age</th>
                    <th>Image</th>
                </tr>

                <jsp:include page="/allgames" />
               
            </table>
        </div>


    </body>
</html>
