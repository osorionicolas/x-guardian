FROM node:17-alpine3.14 as build-stage

COPY . /app
WORKDIR /app
RUN yarn install && \
    yarn build

########################################################################################################################
FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /app/build /usr/share/nginx/html
COPY config/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]