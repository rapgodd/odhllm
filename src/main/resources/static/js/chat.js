document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('chatForm');
    const messageInput = document.getElementById('chatMessageInput');
    const messageContainer = document.getElementById('messageContainer');
    const roomIdElement = document.getElementById('chatRoomId');

    form.addEventListener('submit', (event) => {
        event.preventDefault();

        const messageText = messageInput.value.trim();
        if (!messageText) return;
        messageInput.value = "";

        const chatRoomId = roomIdElement.value;

        const tempAIMessage = document.createElement('div');
        tempAIMessage.classList.add('py-6','py-lg-12');
        tempAIMessage.innerHTML = `
       <div class="message">
        <div class="message-inner">
          <div class="message-body">
            <div class="message-content">
              <div class="message-text">
                <p>...깊이 고민하며 최적의 응답을 생각 중...</p>
              </div>
            </div>
          </div>
          <div class="message-footer">
             <span class="extra-small text-muted"></span>
          </div>
        </div>
       </div>
      </div>
      `;

        const tempUserMessage = document.createElement('div');
        tempUserMessage.classList.add('py-6','py-lg-12');
        tempUserMessage.innerHTML = `
       <div class="message message-out">
        <div class="message-inner">
          <div class="message-body">
            <div class="message-content">
              <div class="message-text">
                <p>${messageText}</p>
              </div>
            </div>
          </div>
          <div class="message-footer">
            <span class="extra-small text-muted"></span>
          </div>
        </div>
       </div>
      </div>


      `;
        messageContainer.appendChild(tempUserMessage);
        messageContainer.appendChild(tempAIMessage);
        tempAIMessage.scrollIntoView({ behavior: 'smooth' });

        fetch("/message/v1", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                message: messageText,
                chatRoomId: chatRoomId
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Server Error');
                }
                return response.json();
            })
            .then(json => {

                const reply = json.data.message;
                tempAIMessage.innerHTML = `
      <div class="py-6 py-lg-12">
       <div class="message">
        <div class="message-inner">
          <div class="message-body">
            <div class="message-content">
              <div class="message-text">
                <p>${reply}</p>
              </div>
            </div>
          </div>
          <div class="message-footer">
             <span class="extra-small text-muted"></span>
          </div>
        </div>
       </div>
      </div>
        `;
                tempAIMessage.scrollIntoView({ behavior: 'smooth' });
            })
            .catch(err => {
                tempAIMessage.innerHTML = `
      <div class="py-6 py-lg-12">
       <div class="message">
        <div class="message-inner">
          <div class="message-body">
            <div class="message-content">
              <div class="message-text">
                <p>다시 시도해 주세요</p>
              </div>
            </div>
          </div>
          <div class="message-footer">
             <span class="extra-small text-muted"></span>
          </div>
        </div>
       </div>
      </div>
        `;
                tempAIMessage.scrollIntoView({ behavior: 'smooth' });
            })
    });
});