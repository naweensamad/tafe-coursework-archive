<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Cabin</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="images/accommodation.png" alt="Accommodation">
        <h1>Insert New Cabin</h1>
    </header>
    <section>
        <form action="insertCabin.php" method="post" enctype="multipart/form-data">
            <label for="cabinType">Cabin Type:</label><br>
            <input type="text" id="cabinType" name="cabinType" required><br><br>
            
            <label for="cabinDescription">Cabin Description:</label><br>
            <textarea id="cabinDescription" name="cabinDescription" required></textarea><br><br>
            
            <label for="pricePerNight">Price Per Night:</label><br>
            <input type="number" id="pricePerNight" name="pricePerNight" required><br><br>
            
            <label for="pricePerWeek">Price Per Week:</label><br>
            <input type="number" id="pricePerWeek" name="pricePerWeek" required><br><br>
            
            <label for="photo">Cabin Photo:</label><br>
            <input type="file" id="photo" name="photo"><br><br>
            
            <input type="submit" value="Insert Cabin">
        </form>

        <?php
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $cabinType = $_POST['cabinType'];
            $cabinDescription = $_POST['cabinDescription'];
            $pricePerNight = $_POST['pricePerNight'];
            $pricePerWeek = $_POST['pricePerWeek'];
            $photo = $_FILES['photo']['name'];
            $photoType = $_FILES['photo']['type'];

            // Validate price
            if ($pricePerNight <= 0) {
                echo "Price per night must be a positive number.<br>";
            } elseif ($pricePerWeek > 5 * $pricePerNight) {
                echo "Price per week must not be more than 5 times the price per night.<br>";
            } else {
                if (!empty($photo)) {
                    $target_dir = "images/";
                    $target_file = $target_dir . basename($photo);
                    if (move_uploaded_file($_FILES['photo']['tmp_name'], $target_file)) {
                        echo "File uploaded successfully.<br>";
                    } else {
                        echo "File upload failed.<br>";
                        $photo = "testcabin.jpg";
                        $photoType = "image/jpeg";
                    }
                } else {
                    $photo = "testcabin.jpg";
                    $photoType = "image/jpeg";
                }

                // Database connection
                $servername = "localhost";
                $username = "root";
                $password = "";
                $dbname = "sunnyspot";

                $conn = new mysqli($servername, $username, $password, $dbname);

                // Check connection
                if ($conn->connect_error) {
                    die("Connection failed: " . $conn->connect_error);
                }

                $sql = "INSERT INTO Cabin (cabinType, cabinDescription, pricePerNight, pricePerWeek, photo)
                        VALUES ('$cabinType', '$cabinDescription', $pricePerNight, $pricePerWeek, '$photo')";

                if ($conn->query($sql) === TRUE) {
                    echo "New cabin inserted successfully.<br>";
                    echo "Selected file: $photo<br>";
                    echo "File type: $photoType<br>";
                } else {
                    echo "Error: " . $sql . "<br>" . $conn->error;
                }

                $conn->close();
            }
        }
        ?>
    </section>
    <footer>
        <a href="./admin\adminMenu.php">Back to Admin Menu</a>
    </footer>
</body>
</html>
