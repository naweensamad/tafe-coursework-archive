<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Cabin</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <header>
        <img src="images/accommodation.png" alt="Accommodation">
        <h1>Delete Cabin</h1>
    </header>
    <section>
        <form action="deleteCabin.php" method="post">
            <label for="cabinID">Select Cabin to Delete:</label><br>
            <select id="cabinID" name="cabinID" required>
                <?php
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

                $sql = "SELECT cabinID, cabinType FROM Cabin";
                $result = $conn->query($sql);

                if ($result->num_rows > 0) {
                    while($row = $result->fetch_assoc()) {
                        echo "<option value='" . $row["cabinID"] . "'>" . $row["cabinType"] . "</option>";
                    }
                } else {
                    echo "<option value=''>No cabins available</option>";
                }

                $conn->close();
                ?>
            </select><br><br>

            <input type="submit" value="Delete Cabin">
        </form>

        <?php
        if ($_SERVER['REQUEST_METHOD'] == 'POST') {
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "sunnyspot";

            $cabinID = $_POST['cabinID'];

            // Database connection
            $conn = new mysqli($servername, $username, $password, $dbname);

            // Check connection
            if ($conn->connect_error) {
                die("Connection failed: " . $conn->connect_error);
            }

            // Fetch cabin type and photo before deleting
            $sql = "SELECT cabinType, photo FROM Cabin WHERE cabinID=$cabinID";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                $cabinType = $row['cabinType'];
                $photo = $row['photo'];

                // Delete cabin
                $sql = "DELETE FROM Cabin WHERE cabinID=$cabinID";
                if ($conn->query($sql) === TRUE) {
                    echo "Cabin deleted successfully. Cabin ID: $cabinID, Cabin Type: $cabinType<br>";
                    if ($photo !== 'testcabin.jpg') {
                        unlink("images/$photo");
                    }
                } else {
                    echo "Error: " . $sql . "<br>" . $conn->error;
                }
            } else {
                echo "No cabin found with ID: $cabinID";
            }

            $conn->close();
        }
        ?>
    </section>
    <footer>
        <a href="./admin/adminMenu.php">Back to Admin Menu</a>
    </footer>
</body>
</html>
