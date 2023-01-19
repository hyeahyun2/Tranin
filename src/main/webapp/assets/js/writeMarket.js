const form = document.getElementById("formId");
const submitBtn = document.getElementById("submitBtn");


function doSubmit() {
	/* 유효성검사 추가하기!! */
	let regExpPrice = /^[0-9]*$/;
	if(form.title.value == ""){
		alert("제목을 입력해주세요!");
		form.title.focus();
		return false;
	}
	if(form.price.value == ""){
		alert("가격을 입력해주세요!");
		form.price.focus();
		return false;
	}
	else if(!regExpPrice.test(form.price.value)){
		alert("가격은 숫자만 사용 가능합니다.")
		form.price.focus();
		return false;
	}
	else if(Number(form.price.value) < 0){
		alert("가격은 0원 이상으로 적어주세요!");
		form.price.focus();
		return false;
	}
	
	form.submit();
	
}

// 등록버튼 클릭 -> 글 등록하기
submitBtn.addEventListener("click", doSubmit);


// let file = new File([imgBlob], fileName,{type:"image/jpeg", lastModified:new Date().getTime()}, 'utf-8');
// let container = new DataTransfer(); 
// container.items.add(file);
// document.querySelector('#file_input').files = container.files;
// https://curryyou.tistory.com/442
// 검색 : js File 객체 생성
/*
	export const convertURLtoFile = async (url: string) => {
  const response = await fetch(url);
  const data = await response.blob();
  const ext = url.split(".").pop(); // url 구조에 맞게 수정할 것
  const filename = url.split("/").pop(); // url 구조에 맞게 수정할 것
  const metadata = { type: `image/${ext}` };
  return new File([data], filename!, metadata);
};
 */
/* 수정페이지 기존 이미지 제거 */
console.log(location.pathname.substring(1));
if(location.pathname.substring(1) == "marketGoEditPage"){
	const imgZone = document.getElementById("att_zone");
	const removeImg_load = document.editPost.removeImg_load;
	const imgDiv_load = document.querySelectorAll(".imgDiv_load");
	const imgImg_load = document.querySelectorAll(".imgImg_load");
	const imgCheck_load = document.querySelectorAll(".imgCheck_load");
	imgCheck_load.forEach((check, index) => {
		check.addEventListener("click", ()=>{
			removeImg_load.value = removeImg_load.value + imgImg_load[index].getAttribute("alt") + ",";
	    imgZone.removeChild(imgDiv_load[index]);
		})
	})
}

/* 업로드한 이미지 미리보기 */
( /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
  imageView = function imageView(att_zone, btn){

    var attZone = document.getElementById(att_zone);
    var btnAtt = document.getElementById(btn)
    var load_files = [];
    var sel_files = [];
    
    btnAtt.onchange = function(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files)
      for(f of fileArr){
        imageLoader(f);
      }
    }  
		
		
    // 탐색기에서 드래그앤 드롭 사용
    attZone.addEventListener('dragenter', function(e){
      e.preventDefault();
      e.stopPropagation();
    }, false)
    
    attZone.addEventListener('dragover', function(e){
      e.preventDefault();
      e.stopPropagation();
      
    }, false)
  
    attZone.addEventListener('drop', function(e){
      var files = {};
      e.preventDefault();
      e.stopPropagation();
      var dt = e.dataTransfer;
      files = dt.files;
      for(f of files){
        imageLoader(f);
      }
      
    }, false)
    

    
    /*첨부된 이미리즐을 배열에 넣고 미리보기 */
    imageLoader = function(file){
      sel_files.push(file);
      var reader = new FileReader();
      reader.onload = function(ee){
        let img = document.createElement('img')
        img.classList.add("imgImg");
        img.classList.add("imgImg_new");
        img.src = ee.target.result;
        attZone.appendChild(makeDiv(img, file));
      }
      
      reader.readAsDataURL(file);
    }
    
    /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
    makeDiv = function(img, file){
      var div = document.createElement('div')
      div.classList.add("imgDiv");
      div.classList.add("imgDiv_new");
      
      var btn = document.createElement('input')
      btn.setAttribute('type', 'button')
      btn.setAttribute('value', 'x')
      btn.setAttribute('delFile', file.name);
      btn.classList.add("imgCheck");
      btn.classList.add("imgCheck_new");
      btn.onclick = function(ev){
        var ele = ev.srcElement;
        var delFile = ele.getAttribute('delFile');
        for(var i=0 ;i<sel_files.length; i++){
          if(delFile== sel_files[i].name){
            sel_files.splice(i, 1);      
          }
        }
        
        dt = new DataTransfer();
        for(f in sel_files) {
          var file = sel_files[f];
          dt.items.add(file);
        }
        btnAtt.files = dt.files;
        var p = ele.parentNode;
        attZone.removeChild(p)
      }
      div.appendChild(img)
      div.appendChild(btn)
      return div
    }
  }
)('att_zone', 'btnAtt')
