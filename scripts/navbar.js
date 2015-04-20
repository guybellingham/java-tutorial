function LmOver(elem, clr){
elem.style.backgroundColor = clr;
elem.children.tags('A')[0].style.color = "#FFFFFF";
elem.style.cursor = 'hand';}

function LmOut(elem, clr){
elem.style.backgroundColor = clr;
elem.children.tags('A')[0].style.color = "#808080";}

function LmDown(elem, clr){
elem.style.backgroundColor = clr;
elem.children.tags('A')[0].style.color = "#FFFFFF";}

function LmUp(path){
location.href = path;}