FROM node:18-alpine

# FROM --platform=linux/amd64 node:18-alpine

WORKDIR /app

COPY . .
ENV VITE_BACKEND_HOST=
RUN npm install


EXPOSE 3000

CMD ["npm", "run", "dev"]