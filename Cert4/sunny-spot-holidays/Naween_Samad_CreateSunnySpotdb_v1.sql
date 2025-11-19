-- Use the existing database
USE SunnySpot;

-- Create Cabin Table
CREATE TABLE Cabin (
    cabinID BIGINT AUTO_INCREMENT,
    cabinType VARCHAR(150) NOT NULL,
    cabinDescription VARCHAR(255),
    pricePerNight BIGINT(10) NOT NULL CHECK (pricePerNight >= 0),
    pricePerWeek DECIMAL(10,2) NOT NULL CHECK (pricePerWeek <= 5 * pricePerNight),
    photo VARCHAR(50),
    PRIMARY KEY (cabinID)
);

-- Create Inclusion Table
CREATE TABLE Inclusion (
    incID BIGINT AUTO_INCREMENT,
    incName VARCHAR(50) NOT NULL,
    incDetails VARCHAR(255),
    PRIMARY KEY (incID)
);

-- Create CabinInclusion Table
CREATE TABLE CabinInclusion (
    cabinIncID BIGINT AUTO_INCREMENT,
    cabinID BIGINT,
    incID BIGINT,
    PRIMARY KEY (cabinIncID),
    FOREIGN KEY (cabinID) REFERENCES Cabin(cabinID),
    FOREIGN KEY (incID) REFERENCES Inclusion(incID)
);

-- Create Admin Table
CREATE TABLE Admin (
    staffID BIGINT AUTO_INCREMENT,
    userName VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(200) NOT NULL,
    address VARCHAR(200) NOT NULL,
    mobile VARCHAR(8) NOT NULL,
    PRIMARY KEY (staffID)
);

-- Create Log Table
CREATE TABLE Log (
    logID BIGINT AUTO_INCREMENT,
    staffID BIGINT,
    loginDateTime TIMESTAMP NOT NULL,
    logoutDateTime TIMESTAMP NOT NULL,
    PRIMARY KEY (logID),
    FOREIGN KEY (staffID) REFERENCES Admin(staffID)
);

-- Inserting sample data into Cabin table
INSERT INTO Cabin (cabinType, cabinDescription, pricePerNight, pricePerWeek, photo) VALUES
('Standard cabin sleeps 4', 'A 2 bedroom cabin with double in main and either double or 2 singles in the second bedroom', 100, 500, 'stCabin.jpg'),
('Standard open plan cabin sleeps 4', 'An open plan cabin with double bed and set of bunks', 120, 600, 'stOpenCabin.jpg');

-- Inserting sample data into Admin table
INSERT INTO Admin (userName, password, firstName, lastName, address, mobile) VALUES
('admin1', 'password1', 'John', 'Doe', '123 Main St', '12345678'),
('admin2', 'password2', 'Jane', 'Smith', '456 Elm St', '87654321');

-- Inserting sample data into Inclusion table
INSERT INTO Inclusion (incName, incDetails) VALUES
('1 bathroom', ''),
('1+ bathroom', '1 bathroom and separate toilet'),
('2 bathroom', ''),
('Air conditioner', 'Reverse cycle'),
('Ceiling fans', ''),
('Bunk bed', ''),
('2 single beds', ''),
('Double bed', ''),
('Dishwasher', ''),
('DVD Player', ''),
('Hair dryer', '');

-- Inserting sample data into CabinInclusion table
INSERT INTO CabinInclusion (cabinID, incID) VALUES
(1, 1), (1, 7), (1, 8),
(2, 2), (2, 6), (2, 8), (2, 11);
