# Use a base image with Java and Maven
FROM maven:3.8-openjdk-11-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the entire project to the container
COPY . /app

# Run the testng.xml using Maven
CMD ["mvn", "test", "-DsuiteXmlFile=testng.xml"]
