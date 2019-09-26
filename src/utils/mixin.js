export default {
  data() {
    return {
      projectId: '', // 账号
      total: 0, // 条目总数
      pageSizes: [15, 30, 45], // 每页显示条数
      pageSize: 15, // 默认显示条数
      currentPage: 1, // 页码数
      domain: '47.106.71.3',
      port: '8080',
    }
  },
  created() {
    this.getProjectId()
  },
  methods: {
    getProjectId() {
      this.projectId = sessionStorage.getItem('pid')
    },

    headerStyle() {
      return {
        color: '#4a4a4a',
        height: '0.35rem',
        lineHeight: '0.35rem',
        backgroundColor: '#c5e8ff',
        border: '1px solid #EBEEF5',
        padding: '0'
      }
    },

    tdStyle() {
      return {
        height: '0.35rem',
        color: '#646464',
        lineHeight: '0.35rem',
        borderLeft: '1px solid #EBEEF5',
        borderBottom: '1px solid #EBEEF5',
        padding: '0'
      }
    },

    // 清空ruleForm
    claerForm(obj) {
      Object.keys(obj).forEach(item => {
        obj[item] = ''
      });
    },

    // 将为对象中的null转换为''
    notNull(obj) {
      Object.keys(obj).forEach(item => {
        if (!obj[item]) {
          obj[item] = ''
        }
      })
      return obj
    },

    // 使用递归复制对象
    // deepCopy(obj) {
    //   let deepObj = {}
    //   for (let key in obj) {
    //     if (obj.hasOwnProperty(key)) {
    //       if(Array.isArray(obj[key])){
    //         deepObj[key] = JSON.parse(JSON.stringify(obj[key]))
    //       }else if(
    //         obj[key] && 
    //         typeof obj[key] === 'object' && 
    //         obj[key] instanceof Date === false && 
    //         obj[key] instanceof RegExp === false
    //       ) {
    //         deepObj[key] = this.deepCopy(obj[key])
    //       }else {
    //         deepObj[key] = obj[key]
    //       }
    //     }
    //   }
    //   return deepObj
    // },

    // 弹窗(0失败 1成功)
    messageBox(str, num) {
      this.$message({
        message: str,
        type: num ? 'success': 'warning'
      })
    }
  }
}