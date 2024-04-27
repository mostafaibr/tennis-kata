# Use a base image with Maven and Java installed
FROM maven:3.9.5

# Set the working directory inside the container
WORKDIR /tennis

# Copy the source code into the container
COPY . /tennis

# Package the tennis application using Maven
RUN mvn package

# Set environment variables to be used as input
ENV score EMPTY
ENV set EMPTY

# Command to run the tennis application with the passed arguments
CMD ["sh", "-c", "java -jar target/tennis.jar $score $set"]