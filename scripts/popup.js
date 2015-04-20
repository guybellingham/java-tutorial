function popup(target){
  if (top.popup_handle == null) {
    top.popup_handle = window.open(target,"PopUpWindow","width=600,height=400,top=5,left=5,resizable=yes,scrollbars=yes,menubar=no,toolbar=no,status=no,location=no");
  } else {
    top.popup_handle.location.href = target;
  }
  top.popup_handle.focus(); 
}