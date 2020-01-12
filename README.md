# Kodetak

  Deniz Karakay - Özgür Kara - Ahmet Akman

# Fikir Nasıl Çıktı

  Görme engelli insanların görme duyusuna dair his geliştirme ile ilgili araştırmaları incelerken dikkatimizi çeken görüntülerin farklı yöntem ve formlarda ses ile görme engelli bireye aktarılması düşüncesinden yola çıkarak bireyin ortamda kendisine yaklaşmakta olan nesneler ile ilgili hissiyatını geliştirmeyi düşündük. 

# Nasıl Geliştirdik

  Bazı ümit verici akademik makalelerde[1] de bahsi geçen gerçek zamanlı nesne tanıma ile bireyin bilgilendirmesi amacı üzerinde projemizi inşa ettik. Genel bağlamda bireyin elindeki kamera ile aldığı görüntülerin işlenerek yine bireye farklı yönlerden gelen sesler olarak yansıtılması projemizi açıklıyor.
  
# Teknik Olarak

  Görme engelli bireyin elinde ya da görüş alanına sabitlenmiş durumdaki cep telefonunun kamerasından alınan görüntü yerel ağ üzerinden veriyi işleyecek olan bilgisayara gönderilir. Bilgisayardaki yazılımımız, yerel ağdan aldığı görüntüyü bilgisayarlı görü ve makine öğrenmesi algoritmalarının yardımıyla işleyerek bireyin görüş alanındaki cisimlere sınıflandırma ve konumlandırma işlemi yapar. Bireyin görüş alanındaki cisimler işlenirken bireye yaklaşan cisimler ayırt edilerek bireye göre konumu ve hangi cisim olduğu bilgisi cep telefonuna iletilir. Cep telefonundaki mobil uygulamamız bu verilere uygun olarak sağ veya sol kulaklıktan cismin boyutu ile orantılı olarak belirli desibel düzeyinde bireye sesler verir. Böylece görme engelli birey yolda yürürken kendisine yaklaşmakta olan cisimleri bir başkasının yardımı olmaksızın belli ölçüde tanıma yetisine sahip olur.

\
  Projemizde yazılımlarımız biribirinden farklı altsistemler üzerinde ve dillerde yazılmıştır. Mobil uygulmamamız Java dilinde ve Flask Framework[2] kullanılarak oluşturulmuştur. Görüntü işleme işlemlerimiz yapıldığı bilgisayarımızda Python dilinde görüntü işlemek için OpenCV[3] bilgisayarlı görü kütüphanesi kullanılmıştır. Nesne tanımlama işlemlerimiz YOLOv3[4] algoritmasını (geri planda TensorFlow[5] çalışıyor) kullandık.

  
 
 # Daha da İleri!
\
  Bireyin kullanıdığı cep telefonunu tüm işlemlerin yapıldığı yer haline getirerek ürün bağımlılığını azaltmak ürünün cazibesini ve zor koşullarda çalışma doğruluğunu artıracaktır.
\
  Görüntü üzerinden tanımlamada görüntü derinliğini yakınsayan makine öğrenmesi algoritmalarının mobil cihazda çalıştırma fikri bireyin ortam hissiyatını artırmada yardımcı olabilir.

# Kaynaklar
\
[1]https://www.researchgate.net/publication/312593672_Let_Blind_People_See_Real-Time_Visual_Recognition_with_Results_Converted_to_3D_Audio
\
[2] https://www.palletsprojects.com/p/flask/
\
[3] https://opencv.org 
\
[4] https://pjreddie.com/darknet/yolo/ 
\
[5] https://www.tensorflow.org/ 
\

 
