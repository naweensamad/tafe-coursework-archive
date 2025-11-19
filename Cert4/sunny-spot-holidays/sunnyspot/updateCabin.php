<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Cabin</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="images/accommodation.png" alt="Accommodation">
        <h1>Update Cabin</h1>
    </header>
    <section>
        <form action="updateCabin.php" method="post" enctype="multipart/form-data">
            <label for="cabinID">Select Cabin to Update:</label><br>
            <select id="cabinID" name="cabinID" required>
                <?php
                // Database connection variables
                $servername = "localhost";
                $username = "root";
                $password = "";
                $dbname = "sunnyspot";

                // Database connection
                $conn = new mysqli($servername, $username, $password, $dbname);

                // Check connection
                if ($conn->connect_error) {
                    die("Connection failed: " . $conn->connect_error);
                }

                $sql = "SELECT cabinID, cabinType FROM Cabin";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo "<option value='" . $row["cabinID"] . "'>" . $row["cabinType"] . "</option>";
                    }
                }

                $conn->close();
                ?>
            </select><br><br>

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
            
            <input type="submit" value="Update Cabin">
        </form>

        <?php
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $cabinID = $_POST['cabinID'];
            $cabinType = $_POST['cabinType'];
            $cabinDescription = $_POST['cabinDescription'];
            $pricePerNight = $_POST['pricePerNight'];
            $pricePerWeek = $_POST['pricePerWeek'];
            $photo = null;

            // Validate price
            if ($pricePerNight <= 0) {
                echo "Price per night must be a positive number.";
            } elseif ($pricePerWeek > 5 * $pricePerNight) {
                echo "Price per week must not be more than 5 times the price per night.";
            } else {
                if (is_uploaded_file($_FILES['photo']['tmp_name'])) {
                    $photo = $_FILES['photo']['name'];
                    $target_dir = "images/";
                    $target_file = $target_dir . basename($photo);
                    move_uploaded_file($_FILES['photo']['tmp_name'], $target_file);
                } else {
                    // Fetch existing photo if not uploaded
                    $conn = new mysqli($servername, $username, $password, $dbname);
                    if ($conn->connect_error) {
                        die("Connection failed: " . $conn->connect_error);
                    }

                    $sql = "SELECT photo FROM Cabin WHERE cabinID=$cabinID";
                    $result = $conn->query($sql);
                    if ($result->num_rows > 0) {
                        $row = $result->fetch_assoc();
                        $photo = $row["photo"] ? $row["photo"] : "testcabin.jpg";
                    } else {
                        $photo = "testcabin.jpg";
                    }
                    $conn->close();
                }

                // Database connection
                $conn = new mysqli($servername, $username, $password, $dbname);

                // Check connection
                if ($conn->connect_error) {
                    die("Connection failed: " . $conn->connect_error);
                }

                $sql = "UPDATE Cabin SET cabinType='$cabinType', cabinDescription='$cabinDescription', pricePerNight=$pricePerNight, pricePerWeek=$pricePerWeek, photo='$photo' WHERE cabinID=$cabinID";

                if ($conn->query($sql) === TRUE) {
                    echo "Cabin updated successfully. Cabin ID: $cabinID, Cabin Type: $cabinType, Photo: $photo.<br>";
                } else {
                    echo "Error: " . $sql . "<br>" . $conn->error;
                }

                $conn->close();
            }
        }
        ?>
    </section>
    <footer>
        <a href="./admin/adminMenu.php">Back to Admin Menu</a>
    </footer>
</body>
</html>
