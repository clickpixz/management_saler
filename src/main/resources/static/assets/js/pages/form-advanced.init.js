!function (e) {
    "use strict";
    var i = function () {
    };
    i.prototype.initSwitchery = function () {
        e('[data-plugin="switchery"]').each(function (i, t) {
            new Switchery(e(this)[0], e(this).data())
        })
    }
    , i.prototype.initSelect2 = function () {
        e('[data-toggle="select2"]').select2()
    }, i.prototype.initInputmask = function () {
        e('[data-toggle="input-mask"]').each(function (i, t) {
            var n = e(t).data("maskFormat"), o = e(t).data("reverse");
            null != o ? e(t).mask(n, {reverse: o}) : e(t).mask(n)
        })
    }, i.prototype.initTouchspin = function () {
        var o = {};
        e('[data-toggle="touchspin"]').each(function (i, t) {
            var n = e.extend({}, o, e(t).data());
            e(t).TouchSpin(n)
        }), e("input[name='demo3_21']").TouchSpin({initval: 40}), e("input[name='demo3_22']").TouchSpin({initval: 40})
    }, i.prototype.initTimepicker = function () {
        e("#timepicker").timepicker({
            defaultTIme: !1,
            icons: {up: "mdi mdi-chevron-up", down: "mdi mdi-chevron-down"}
        }), e("#timepicker2").timepicker({
            showMeridian: !1,
            icons: {up: "mdi mdi-chevron-up", down: "mdi mdi-chevron-down"}
        }), e("#timepicker3").timepicker({
            minuteStep: 15,
            icons: {up: "mdi mdi-chevron-up", down: "mdi mdi-chevron-down"}
        })
    }, i.prototype.initColorpicker = function () {
        e("#default-colorpicker").colorpicker({format: "hex"}), e("#rgba-colorpicker").colorpicker(), e("#component-colorpicker").colorpicker({format: null})
    }, i.prototype.init = function () {
        this.initSwitchery(), this.initSelect2(), this.initInputmask(), this.initTouchspin(), this.initTimepicker(), this.initColorpicker()
    }, e.Components = new i, e.Components.Constructor = i
}(window.jQuery), function (i) {
    "use strict";
    window.jQuery.Components.init()
}();