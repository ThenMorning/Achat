'use strict';
module.exports = app => {
  app.io.route('/chat', app.controller.chat.ping);
};
