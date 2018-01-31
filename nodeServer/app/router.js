'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  require('./router/login')(app);
  require('./router/register')(app);
  require('./router/io')(app);
};