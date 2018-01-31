'use strict';


module.exports = app => {
  class LoginController extends app.Controller {
    async index() {
      const query = this.ctx.query;
      const username = query.username;
      const password = query.password;
      let result = await this.service.user.getUserByUserName(username);
      if (result.length === 0) {
        result = await this.service.util.resultConstruction(result[0], false, '用户名不存在!');
      } else {
        if (result[0].password === password) {
          result = await this.service.util.resultConstruction({}, true, '登录成功!');
        } else {
          result = await this.service.util.resultConstruction({}, false, '密码错误!');
        }
      }
      this.ctx.body = result;
    }
  }
  return LoginController;
};
