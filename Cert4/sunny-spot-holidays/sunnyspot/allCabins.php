<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Cabins</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="images/accommodation.png" alt="Accommodation">
        <h1>Sunnyspot Accommodation</h1>
    </header>
    <section>
        <?php
        // Database connection
        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "sunnyspot";

        // Create connection
        $conn = new mysqli($servername, $username, $password, $dbname);

        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        // Fetch cabins
        $sql = "SELECT cabinType, cabinDescription, pricePerNight, pricePerWeek, photo FROM Cabin";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            // Output data of each row
            while($row = $result->fetch_assoc()) {
                $photoPath = "images/" . $row["photo"];
                if (!file_exists($photoPath)) {
                    $photoPath = "images/testcabin.jpg";
                }
                echo "<article>";
                echo "<h2>" . $row["cabinType"] . "</h2>";
                echo "<img src='" . $photoPath . "' alt='" . $row["cabinType"] . "'>";
                echo "<p><span>Details: </span>" . $row["cabinDescription"] . "</p>";
                echo "<p><span>Price per night: </span>$" . $row["pricePerNight"] . "</p>";
                echo "<p><span>Price per week: </span>$" . $row["pricePerWeek"] . "</p>";
                echo "</article>";
            }
        } else {
            echo "0 results";
        }

        $conn->close();
        ?>
    </section>
    <footer>
        <a href="./admin/adminMenu.php">Admin</a>
    </footer>
</body>
</html>
