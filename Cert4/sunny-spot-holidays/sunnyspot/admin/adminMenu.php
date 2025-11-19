<?php
session_start();
 
// Check if the user is logged in
if (!isset($_SESSION['loggedin']) || $_SESSION['loggedin'] !== true) {
    header("Location: login.php");
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Menu</title>
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="../images\accommodation.png" alt="Accommodation">
        <h1>Sunnyspot Accommodation - Admin Menu</h1>
    </header>
    <section>
        <ul>
            <li><a href="../insertCabin.php">Insert a new cabin</a></li>
            <li><a href="../updateCabin.php">Update a cabin</a></li>
            <li><a href="../deleteCabin.php">Delete a cabin</a></li>
        </ul>
    </section>
    <footer>
    <a href="../allCabins.php">Back to Cabins</a>
    <br>
    <a href="logout.php">Logout</a>
</footer>

</body>
</html>
