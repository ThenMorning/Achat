'use strict';
module.exports = app => {
  app.router.get('/register', app.controller.register.index);
};
