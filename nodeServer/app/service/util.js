'use strict';
module.exports = app => {
  return class UtilService extends app.Service {
    async resultConstruction(result, _success, _message) {
      const _result = {
        data: result || {},
        status: {
          success: _success,
          message: _message,
        },
      };
      return _result;
    }
  };
};
