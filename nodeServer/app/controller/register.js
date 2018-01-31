'use strict';


module.exports = app => {
  class RegisterController extends app.Controller {
    async index() {
      const query = this.ctx.query;
      const username = query.username;
      const password = query.password;
      let result = null;
      if (username && password) {
        const res = await this.service.user.addUser(username, password);
        const line = res ? res.affectedRows : null;
        if (line) {
          result = await this.service.util.resultConstruction({}, true, '注册成功!');
        } else {
          result = await this.service.util.resultConstruction({}, false, '用户名已存在,注册失败!');
        }
      } else {
        result = await this.service.util.resultConstruction({}, false, '用户名或密码不符合要求!');
      }
      this.ctx.body = result;
      // if (result.length === 0) {
      //   result = yield this.service.util.resultConstruction(result[0], false, '未找到');
      // } else {
      //   result = yield this.service.util.resultConstruction(result[0], true, '');
      // }
      // this.ctx.body = result;
    }
  }
  return RegisterController;
};
