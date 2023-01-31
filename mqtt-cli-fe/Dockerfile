FROM node:14
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm install react-scripts
RUN npm run build
EXPOSE 3000
CMD [ "npm", "run", "start" ]