'use strict';
module.exports = app => {
  return class UserService extends app.Service {
    async getAllUser() {
      // const result = await this.app.mysql.query('select * from user;', '');
      const result = await app.mysql.select(
        'user',
        {
          limit: 10,
          offset: 0,
        }
      );
      return result;
    }
    async getUserByUserName(_username) {
      // const result = await this.app.mysql.query('select * from user where username = ?', username);
      const result = await app.mysql.select(
        'user',
        {
          where: { username: _username },
          limit: 10,
          offset: 0,
        }
      );
      return result;
    }
    async addUser(_username, _password) {
      const res = await this.service.user.getUserByUserName(_username);
      let result = null;
      if (!res.length) {
        result = await this.app.mysql.insert('user', { username: _username, password: _password });
      }
      return result;
    }
  };
};
