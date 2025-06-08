# المرحلة 1: بناء تطبيق Angular
FROM node:18 AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --configuration production

# المرحلة 2: تقديم الملفات باستخدام Nginx
FROM nginx:alpine
COPY --from=build /app/dist/hello-event-frontend /usr/share/nginx/html
EXPOSE 80
