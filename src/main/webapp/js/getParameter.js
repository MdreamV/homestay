function getParameter(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)","i");
    var r=location.search.substr(1).match(reg);
    if(r != null)
        return r[2];
    return null;

}
//格式化货币
        function formatPrice(value) {
        let val = (value/1).toFixed(2).replace(',', '.');
        return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      }