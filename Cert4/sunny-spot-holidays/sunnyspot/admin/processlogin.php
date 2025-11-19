<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
session_start();

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'] ?? null;
    $password = $_POST['password'] ?? null;

    // Define valid credentials
    $validCredentials = array(
        'username' => 'admin',
        'password' => 'secure'
    );

    // Check if both username and password are provided
    if (isset($_POST['username']) && isset($_POST['password'])) {
        $username = $_POST['username'];
        $password = $_POST['password'];
    }

    if ($username === $validCredentials['username'] && $password === $validCredentials['password']) {
        // Set session variables and redirect to adminMenu.php
        $_SESSION['loggedin'] = true;
        $_SESSION['username'] = $username;
        header("Location: adminMenu.php");
        exit();
    } else {
        echo "Invalid credentials.";
    }
}
?>
