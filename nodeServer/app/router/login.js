'use strict';
module.exports = app => {
  app.router.get('/login', app.controller.login.index);
};
