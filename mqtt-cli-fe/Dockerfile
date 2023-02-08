# Use a node image as the base image
FROM node:14

# Set the working directory in the container to /app
WORKDIR /app

# Copy the package.json and package-lock.json to the container
COPY package*.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the app files to the container
COPY . .

# Install react-scripts globally
RUN npm install -g react-scripts

# Build the React app
RUN npm run build

# Expose port 3000 for incoming traffic to the container
EXPOSE 3000

# Start the React app
CMD [ "npm", "run", "start" ]
