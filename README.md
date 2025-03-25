
![image](https://github.com/user-attachments/assets/fadb8089-9143-4290-a3c9-de38cdb1baab)

Link download data: https://postgrespro.com/docs/postgrespro/12/demodb-bookings-installation.html

**Danh sách các bảng trong database Airline, bao gồm:**

aircrafts_data <br>
airports_data <br>
boarding_passes <br>
bookings <br>
flights <br>
seats <br>
ticket_flights <br>
tickets<br>
Đây là một schema điển hình cho hệ thống đặt vé máy bay. Tôi sẽ phân tích và chỉ ra mối quan hệ giữa các bảng dựa trên tên và ý nghĩa của chúng, vì đây là các bảng phổ biến trong hệ thống hàng không.
<br>
![image](https://github.com/user-attachments/assets/ead1e108-120f-4f7f-abf8-286c306fd5d2)

**1. Tổng quan về các bảng**
Dựa trên tên bảng, chúng thể suy ra ý nghĩa và vai trò của chúng:
<br>
**aircrafts_data**: Lưu thông tin về máy bay (mã máy bay, loại, phạm vi bay, v.v.).<br>
**airports_data**: Lưu thông tin về sân bay (mã sân bay, tên, thành phố, v.v.).<br>
**boarding_passes**: Lưu thông tin thẻ lên máy bay (số vé, số ghế, thời gian lên máy bay, v.v.).<br>
**bookings**: Lưu thông tin đặt chỗ (mã đặt chỗ, ngày đặt, tổng tiền, v.v.).<br>
**flights**: Lưu thông tin chuyến bay (mã chuyến bay, sân bay đi/đến, thời gian, máy bay, v.v.).<br>
**seats**: Lưu thông tin ghế trên máy bay (mã máy bay, số ghế, loại ghế, v.v.).<br>
**ticket_flights**: Bảng trung gian, liên kết vé và chuyến bay (mã vé, mã chuyến bay, giá vé, v.v.).<br>
**tickets**: Lưu thông tin vé (mã vé, mã đặt chỗ, thông tin hành khách, v.v.).<br>
<br>
**2. Mối quan hệ giữa các bảng**<br>
Dựa trên ý nghĩa, tôi sẽ suy ra các mối quan hệ (relationships) giữa các bảng, thường được thiết lập qua khóa chính (primary key) và khóa ngoại (foreign key).
<br>
**a. bookings và tickets**
Mối quan hệ: Một đặt chỗ (bookings) có thể có nhiều vé (tickets) - Quan hệ 1:N (một-nhiều).<br>
Khóa liên kết:<br>
bookings có khóa chính là book_ref (mã đặt chỗ).<br>
tickets có khóa ngoại là book_ref, tham chiếu đến bookings(book_ref).<br>
Ý nghĩa: Một khách hàng đặt chỗ (booking) có thể mua nhiều vé cho nhiều hành khách.<br>
<br>
**b. tickets và ticket_flights**<br>
Mối quan hệ: Một vé (tickets) có thể liên quan đến nhiều chuyến bay (flights) qua bảng trung gian ticket_flights - Quan hệ N:N (nhiều-nhiều).<br>
Khóa liên kết:<br>
tickets có khóa chính là ticket_no (mã vé).<br>
ticket_flights có khóa ngoại ticket_no tham chiếu tickets(ticket_no) và flight_id tham chiếu flights(flight_id).<br>
Ý nghĩa: Một vé có thể bao gồm nhiều chuyến bay (ví dụ: chuyến bay nối chuyến), và một chuyến bay có thể có nhiều vé.<br>
<br>
**c. flights và ticket_flights**<br>
Mối quan hệ: Một chuyến bay (flights) có thể có nhiều vé, được liên kết qua ticket_flights - Quan hệ 1:N (một-nhiều) từ flights đến ticket_flights.<br>
Khóa liên kết:<br>
flights có khóa chính là flight_id (mã chuyến bay).<br>
ticket_flights có khóa ngoại flight_id tham chiếu flights(flight_id).<br>
Ý nghĩa: Bảng ticket_flights lưu thông tin chi tiết (như giá vé) cho từng vé trên từng chuyến bay.<br>
<br>
**d. flights và aircrafts_data**<br>
Mối quan hệ: Một chuyến bay (flights) được thực hiện bởi một máy bay (aircrafts_data) - Quan hệ N:1 (nhiều-một).<br>
Khóa liên kết:<br>
aircrafts_data có khóa chính là aircraft_code (mã máy bay).<br>
flights có khóa ngoại aircraft_code tham chiếu aircrafts_data(aircraft_code).<br>
Ý nghĩa: Mỗi chuyến bay được gán cho một máy bay cụ thể.<br>
<br>
**e. flights và airports_data**<br>
Mối quan hệ: Một chuyến bay (flights) có sân bay đi và sân bay đến, liên kết với airports_data - Quan hệ N:1 (nhiều-một) cho cả sân bay đi và đến.<br>
Khóa liên kết:<br>
airports_data có khóa chính là airport_code (mã sân bay).<br>
flights có hai khóa ngoại:<br>
departure_airport tham chiếu airports_data(airport_code).<br>
arrival_airport tham chiếu airports_data(airport_code).<br>
Ý nghĩa: Mỗi chuyến bay có sân bay khởi hành và sân bay đích.<br>
<br>
**f. seats và aircrafts_data**<br>
Mối quan hệ: Một máy bay (aircrafts_data) có nhiều ghế (seats) - Quan hệ 1:N (một-nhiều).<br>
Khóa liên kết:<br>
aircrafts_data có khóa chính là aircraft_code.<br>
seats có khóa ngoại aircraft_code tham chiếu aircrafts_data(aircraft_code).<br>
Ý nghĩa: Mỗi máy bay có danh sách ghế (ví dụ: 1A, 1B, v.v.).<br>
<br>
**g. boarding_passes và ticket_flights**<br>
Mối quan hệ: Một vé-chuyến bay (ticket_flights) có một thẻ lên máy bay (boarding_passes) - Quan hệ 1:1 (một-một).<br>
Khóa liên kết:<br>
ticket_flights có khóa chính là cặp (ticket_no, flight_id).<br>
boarding_passes có khóa ngoại (ticket_no, flight_id) tham chiếu ticket_flights(ticket_no, flight_id).<br>
Ý nghĩa: Mỗi vé trên một chuyến bay được cấp một thẻ lên máy bay (boarding pass) khi check-in.<br>
<br>
**h. boarding_passes và seats**<br>
Mối quan hệ: Một thẻ lên máy bay (boarding_passes) được gán cho một ghế (seats) - Quan hệ N:1 (nhiều-một).<br>
Khóa liên kết:<br>
seats có khóa chính là (aircraft_code, seat_no).<br>
boarding_passes có khóa ngoại (aircraft_code, seat_no) tham chiếu seats(aircraft_code, seat_no).<br>
Ý nghĩa: Mỗi thẻ lên máy bay chỉ định ghế cụ thể trên máy bay.
